package targets.services.command;

import com.netpilot.api.targets.dtos.TargetCreateRequest;
import com.netpilot.api.targets.dtos.TargetResponse;
import com.netpilot.api.targets.model.TargetType;
import com.netpilot.api.targets.model.Targets;
import com.netpilot.api.targets.repository.TargetRepository;
import com.netpilot.api.targets.service.command.TargetCommandService;
import com.netpilot.api.targets.service.command.impl.TargetCommandServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TargetCommandServiceImplTest {

    @Mock
    private TargetRepository targetRepository;

    @InjectMocks
    private TargetCommandServiceImpl targetCommandService;

    private static final Targets target1 =
            Targets.builder().slug("Slug1").name("Name1").type(TargetType.DEVICE).build();
    private static final Targets target2 =
            Targets.builder().id(2L).slug("Slug2").name("Name2").type(TargetType.CONTAINER).build();
    private static final Targets target3 =
            Targets.builder().id(3L).slug("Slug3").name("Name3").type(TargetType.CONTAINER).build();


    @Test
    void createTargetCreates(){
        TargetCreateRequest request = new TargetCreateRequest("Slug1","Name1",TargetType.DEVICE);
        Targets saved = new Targets(1L,"Slug1","Name1",TargetType.DEVICE);

        when(targetRepository.save(target1)).thenReturn(saved);
        TargetResponse response = targetCommandService.createTarget(request);

        assertThat(response.id()).isEqualTo(saved.getId());
        assertThat(response.slug()).isEqualTo(saved.getSlug());
        assertThat(response.name()).isEqualTo(saved.getName());
        assertThat(response.type()).isEqualTo(saved.getType());

    }

}
