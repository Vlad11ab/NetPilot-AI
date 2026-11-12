package targets.services;

import com.netpilot.api.targets.constants.TargetConstants;
import com.netpilot.api.targets.dtos.TargetResponse;
import com.netpilot.api.targets.exceptions.TargetNotFoundException;
import com.netpilot.api.targets.model.TargetType;
import com.netpilot.api.targets.model.Targets;
import com.netpilot.api.targets.repository.TargetRepository;
import com.netpilot.api.targets.service.query.TargetQueryService;
import com.netpilot.api.targets.service.query.impl.TargetQueryServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import org.testcontainers.shaded.org.bouncycastle.asn1.cmp.Challenge;


import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TargetQueryServiceImplTest {

    @Mock
    private TargetRepository targetRepository;

    @InjectMocks
    private TargetQueryServiceImpl targetQueryService;

    private static final Targets target1 =
            Targets.builder().id(1L).slug("Slug1").name("Name1").type(TargetType.DEVICE).build();
    private static final Targets target2 =
            Targets.builder().id(2L).slug("Slug2").name("Name2").type(TargetType.CONTAINER).build();
    private static final Targets target3 =
            Targets.builder().id(3L).slug("Slug3").name("Name3").type(TargetType.CONTAINER).build();


    @Test
    void listTargets_returnsList(){
        Pageable pageable =  PageRequest.of(0,10);
        Targets target = new Targets();
        target.setId(1L);
        target.setSlug("web-01");
        target.setName("Web server");
        target.setType(TargetType.DEVICE);

        when(targetRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(List.of(target), pageable, 1L));

        Page<TargetResponse> result = targetQueryService.listTargets(pageable);

        assertThat(result.getTotalElements()).isEqualTo(1);
        TargetResponse response = result.getContent().get(0);
        assertThat(response.slug()).isEqualTo("web-01");
        assertThat(response.name()).isEqualTo("Web server");
        assertThat(response.type()).isEqualTo(TargetType.DEVICE);

        verify(targetRepository).findAll(pageable);
    }

    @Test
    void getTarget_ThrowsWhenNotFound(){
        Random random = new Random();
        Long missingId = 9L;

        when(targetRepository.findById(missingId)).thenReturn(Optional.empty());

        assertThatThrownBy(()-> targetQueryService.getTarget(missingId))
                .isInstanceOf(TargetNotFoundException.class)
                .hasMessage(TargetConstants.TARGET_NOT_FOUND);
    }

    @Test
    void getTarget_ReturnsTarget(){
        when(targetRepository.findById(target1.getId())).thenReturn(Optional.of(target1));

        TargetResponse response = new TargetResponse(1L,"Slug1","Name1",TargetType.DEVICE);

        assertThat(targetQueryService.getTarget(target1.getId())).isEqualTo(response);
    }

}
