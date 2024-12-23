package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should return the player in the team with best scores"
    name("GET return the player in the team with best scores")
    request {
        method GET()
        url(regex("/player/[a-zA-Z0-9]+/bestPlayer"))
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body(
                name      : $(consumer("Test"),producer(anyNonEmptyString())),
                role      : $(consumer("Forward"),producer(regex("(Forward|Midfielder|Defender|Goalkeeper)"))),
                age       : $(consumer(24),producer(anyPositiveInt())),
                goals     : $(consumer(10),producer(anyPositiveInt())),
                team      : $(consumer("Test"),producer(anyNonEmptyString())),
                gender    : $(consumer('Male'),producer(anyOf('Male','Female'))),
                height    : $(consumer(183),producer(anyPositiveInt())),
                weight    : $(consumer(72),producer(anyPositiveInt())),
                injuries : [
                        [
                                year :$(consumer(2021),producer(regex("[1-9][0-9]{3}"))),
                                type: $(consumer("Test"),producer(anyNonEmptyString()))
                        ]
                ],
                oldTeams : [
                        [
                                year :$(consumer(2019),producer(regex("[1-9][0-9]{3}"))),
                                team: $(consumer("Test"),producer(anyNonEmptyString()))
                        ]
                ]
                )

    }
}
