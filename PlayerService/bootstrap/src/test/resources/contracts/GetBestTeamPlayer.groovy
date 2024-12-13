package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should return the player in the team with best scores"
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

    }
}
