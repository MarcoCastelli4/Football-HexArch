package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should return the player in the team with best scores"
    request {
        method GET()
        urlPath("player/Juventus/bestPlayer") {
            queryParameters {
                parameter("team", "Juventus") // Request filtered by team name
            }
        }
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body(
                [
                        name      : "Cristiano Ronaldo",
                        role  : "Striker",
                        age       : 36,
                        goals: 700,
                        team      : "Juventus",
                        gender: "Male",
                        "height": 187,
                        "weight":83,
                        injuries  : [
                                "2010": "Knee injury",
                                "2020": "Hamstring injury"
                        ],
                        oldTeams  : [
                                "2010": "Real Madrid",
                                "2007": "Manchester United"
                        ]
                ]
                )

    }
}
