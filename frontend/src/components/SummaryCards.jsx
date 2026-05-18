import "../styles/SummaryCards.css";

function SummaryCards({
    instances,
    buckets,
    checks,
    passedChecks
}) {

    return (

        <div className="cards">

            <div className="card">
                <h3>EC2 Instances</h3>
                <h2>{instances}</h2>
            </div>

            <div className="card">
                <h3>S3 Buckets</h3>
                <h2>{buckets}</h2>
            </div>

            <div className="card">
                <h3>CIS Checks</h3>
                <h2>{checks}</h2>
            </div>

            <div className="card">
                <h3> Checks Passed</h3>
                <h2 className="success">
                    {passedChecks}
                </h2>
            </div>

        </div>
    );
}

export default SummaryCards;