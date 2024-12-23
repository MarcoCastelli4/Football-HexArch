package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {

    name("PUT Add player to the team")
    description "Should add a player to the team and return success message"

    request {
        method PUT()
        url "/player/addPlayer"
        body(
                name      : $(producer("Test"),consumer(anyNonEmptyString())),
                role      : $(producer("Forward"),consumer(regex("(Forward|Midfielder|Defender|Goalkeeper)"))),
                age       : $(producer(24),consumer(anyPositiveInt())),
                goals     : $(producer(10),consumer(anyPositiveInt())),
                team      : $(producer("Test"),consumer(anyNonEmptyString())),
                gender    : $(producer('Male'),consumer(anyOf('Male','Female'))),
                height    : $(producer(183),consumer(anyPositiveInt())),
                weight    : $(producer(72),consumer(anyPositiveInt())),
                injuries : [
                        [
                                year :$(producer(2021),consumer(regex("[1-9][0-9]{3}"))),
                                type: $(producer("Test"),consumer(anyNonEmptyString()))
                        ]
                ],
                oldTeams : [
                        [
                                year :$(producer(2019),consumer(regex("[1-9][0-9]{3}"))),
                                team: $(producer("Test"),consumer(anyNonEmptyString()))
                        ]
                ]

        )

        headers {
            contentType applicationJson()
        }
    }

    response {
        status OK()
        body("Player added successfully")
        headers {
            contentType textPlain()
        }
    }
}

