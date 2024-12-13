package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should add a player to the team and return success message"

    request {
        method PUT()
        url "/player/addPlayer"
        body(
                name      : $(anyNonEmptyString()),
                role      : $(regex("(Forward|Midfielder|Defender|Goalkeeper)")),
                age       : $(anyPositiveInt()),
                goals     : $(anyPositiveInt()),
                team      : $(anyNonEmptyString()),
                gender    : $(anyOf('Male','Female')),
                height    : $(anyPositiveInt()),
                weight    : $(anyPositiveInt()),
                injuries : [
                        [
                                year :$(regex("[1-9][0-9]{3}")),
                                type: $(anyNonEmptyString())
                        ]
                ],
                oldTeams : [
                        [
                                year :$(regex("[1-9][0-9]{3}")),
                                team: $(anyNonEmptyString())
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

