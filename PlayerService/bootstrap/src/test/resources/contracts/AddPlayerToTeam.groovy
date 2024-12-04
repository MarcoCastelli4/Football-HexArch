package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Should add a player to the team and return success message"

    request {
        method PUT()
        url "/player/addPlayer"
        body([
                name      : "Marco Castelli",
                role  : "Defender",
                age       : 24,
                goals: 35,
                team      : "Juventus",
                gender: "Male",
                "height": 183,
                "weight":70,
                injuries  : [
                        "2016": "Ankle injury",
                        "2017": "Ankle injury",
                        "2018": "Hamstring injury",
                        "2022": "Ankle injury",
                        "2023": "Ankle injury",
                        "2024": "Hamstring injury",
                ],
                oldTeams  : [
                        "2016": "Darfo Boario",
                        "2018": "Us Breno"
                ]
        ])
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

