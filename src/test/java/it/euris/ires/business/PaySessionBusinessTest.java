package it.euris.ires.business;

import it.euris.ires.dataObject.CreatePaySessionRequest;
import it.euris.ires.dataObject.CreatePaySessionResponse;
import it.euris.ires.entity.PaySession;
import it.euris.ires.exception.PaySessionException;
import it.euris.ires.service.IPaymentSessionService;
import it.euris.ires.util.PaySessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PaySessionBusinessTest {

    PaySessionBusiness paySessionBusiness;

    @Mock
    IPaymentSessionService paymentSessionServiceMock;

    @BeforeEach
    void setUp() {
        paySessionBusiness = new PaySessionBusiness(paymentSessionServiceMock);
    }

    @Test
    void givenRequestWhenCreatePaySessionThenSuccessTrueThenId() throws PaySessionException {
        CreatePaySessionRequest request = new CreatePaySessionRequest();
        PaySession paySession = new PaySession();
        paySession.setStatus(PaySessionStatus.CREATED);

        given(paymentSessionServiceMock.createWebPaySession(request)).willReturn(paySession);

        CreatePaySessionResponse response = paySessionBusiness.createPaySession(request);

        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getPaySessionId()).isEqualTo(paySession.getUuid().toString());
    }

}
