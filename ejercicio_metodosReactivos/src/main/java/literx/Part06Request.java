package literx;


import literx.domain.User;
import literx.repository.ReactiveRepository;
import literx.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
public class Part06Request {

	ReactiveRepository<User> repository = new ReactiveUserRepository();

//========================================================================================

	// TODO Create a StepVerifier that initially requests all values and expect 4 values to be received
	StepVerifier requestAllExpectFour(Flux<User> flux) {
        return StepVerifier.create(flux)
                .expectNextCount(4).expectComplete();
    }
//========================================================================================

	// TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE then stops verifying by cancelling the source
	StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
		return StepVerifier.create(flux,0)// el n me indica la cantidad de mensajes que quiero que me lleguen.
                .thenRequest(1) // la cantidad de mensajes que quiero recibir
                .expectNext(User.SKYLER)// compara
                .thenRequest(1)
                .expectNext(User.JESSE)
                .thenCancel();//cancela el flujo

	}

//========================================================================================

	// TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
	Flux<User> fluxWithLog() {
		return repository.findAll().log();
	}

//========================================================================================

	// TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
	Flux<User> fluxWithDoOnPrintln() {
		return repository.findAll().doOnSubscribe(subscription -> System.out.println("Starring:"))
                .doOnNext(p-> System.out.println(p.getFirstname()+" "+p.getLastname()))
                .doOnComplete(()-> System.out.println("The end!"));
	}

}
