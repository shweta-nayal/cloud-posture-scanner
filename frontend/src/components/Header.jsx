import "../styles/Header.css";

function Header({ loading, onScan }) {

    return (

        <div className="header">

            <div>

                <h1>Cloud Posture Scanner</h1>

                <p>
                    AWS security monitoring dashboard
                </p>

            </div>

            <button
                className="scan-btn"
                onClick={onScan}
            >

                {
                    loading
                        ? "Scanning..."
                        : "Run Scan"
                }

            </button>

        </div>
    );
}

export default Header;