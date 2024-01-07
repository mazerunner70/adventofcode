


export default function DetailHeader({config}:{ config: any }) {
    return (
        <div>
            <h1>Detail View</h1>
            <p>{config.dayno}: {config.envName}</p>
        </div>
    )
}
