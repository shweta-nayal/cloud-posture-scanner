import "../styles/CISResults.css";

function CISResults({ results }) {

    return (

        <div className="section">

            <h2>CIS Security Results</h2>

            <p>
                AWS CIS Benchmark outcomes with evidence
            </p>

            <div className="cis-grid">

                {
                    results.map((result, index) => (

                        <div
                            className="cis-card"
                            key={index}
                        >

                            <div className="cis-top">

                                <h3>{result.checkName}</h3>

                                <span
                                    className={
                                        result.status === "PASS"
                                            ? "badge success-bg"
                                            : "badge danger-bg"
                                    }
                                >

                                    {result.status}

                                </span>

                            </div>

                            <p>{result.evidence}</p>

                        </div>
                    ))
                }

            </div>

        </div>
    );
}

export default CISResults;