import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.management.RuntimeErrorException;

public class MonoFluxTest {

    @Test
    public void monoTest() {
     Mono<?> mono = Mono.just("ABS")
             .then(Mono.error(new RuntimeException("Event stream error")))
             .log();
     mono.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void fluxTest() {
        Flux<?> flux =  Flux.just("Spring", "Boot", "Hibernate", "Microservice")
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("Reactive error on the way")))
                .log();
        flux.subscribe(System.out::println,(e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void fluxTestTwo() {
        Flux<?> flux = Flux.just(1, 2, 3)
                .transformDeferredContextual(Flux::contextWrite)
                .concatWith(Flux.error(new RuntimeException("Error")))
                .concatWith(Flux.just(4, 5))
                .log()
                .onErrorResume(ex -> {
            throw new RuntimeException("Invalid flux implementation ", ex);
        });
        flux.subscribe(System.out::println, (e) ->
            System.err.println("Exception is " + e),
                () -> System.out.println("Completed"));
    }

    @Test
    public void fluxTestElements_WithoutError(){
        Flux<String> flux = Flux.just("Spring", "DevOps", "Reactive WebFlux")
                .log();

        StepVerifier.create(flux)
                .expectNext("Spring")
                .expectNext("DevOps")
                .expectNext("Reactive WebFlux")
                .verifyComplete();
    }

    @Test
    public void fluxTestElements_WithError(){
        Flux<String> flux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Invalid reactive implementation of flux")))
                .log();

        StepVerifier.create(flux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                //.expectError(RuntimeException.class)
                .expectErrorMessage("Invalid reactive implementation of flux")
                .verify();
    }

    @Test
    public void fluxTestElementsCount_WithError(){
        Flux<String> flux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Invalid reactive implementation of flux")))
                .log();

        StepVerifier.create(flux)
                .expectNextCount(3)
                //.expectError(RuntimeException.class)
                .expectErrorMessage("Invalid reactive implementation of flux")
                .verify();
    }

    @Test
    public void fluxTestWithError(){
        Flux<String> flux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Invalid reactive implementation of flux")))
                .log();

        StepVerifier.create(flux)
                .expectNext("Spring", "Spring Boot", "Reactive Spring")
                //.expectError(RuntimeException.class)
                .expectErrorMessage("Invalid reactive implementation of flux")
                .verify();
    }

    @Test
    public void mono_verify_value(){
      Mono<String> stringMono = Mono.just("Spring");

      StepVerifier.create(stringMono.log())
              .expectNext("Spring")
              .verifyComplete();
    }

    @Test
    public void mono_on_error(){
        StepVerifier.create(Mono.error(new RuntimeException("Exception occurred")).log())
                .expectError(RuntimeException.class)
                .verify();
    }
}

