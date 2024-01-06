

import {Container} from "./styled"

export default function AdventDayEnv({dayEnvConfig, envName}:{dayEnvConfig: any, envName}) {
    <h3>{envName}</h3>
    <Container
        <div className="progress-bar__container">
            <div className="progress-bar">
                <span className="progress-bar__text">Uploaded Successfully!</span>
            </div>
        </div>
    </Container>
}

//look here for progress bar details
//https://alvarotrigo.com/blog/progress-bar-css/