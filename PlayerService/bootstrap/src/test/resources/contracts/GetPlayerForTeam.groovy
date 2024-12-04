package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should return a list of players for a given team"
    request {
        method GET()
        urlPath("/player/Juventus/all") {
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
        body([
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
                ],
                [
                         name      : "Leonardo Bonucci",
                         role  : "Defender",
                         age       : 36,
                         goals: 40,
                         team      : "Juventus",
                         gender: "Male",
                         "height": 187,
                         "weight":84,
                         injuries  : [
                                 "2020": "Knee injuries"
                         ],
                         oldTeams  : [
                                 "2010": "Bari"
                         ]
                 ]
                ])

    }
}
