import "../styles/Table.css";

function S3Table({ buckets }) {

    return (

        <div className="section">

            <h2>S3 Buckets</h2>

            <p>
                Storage buckets with encryption and access posture
            </p>

            <table>

                <thead>

                <tr>

                    <th>Bucket Name</th>
                    <th>Region</th>
                    <th>Encrypted</th>
                    <th>Public / Private</th>

                </tr>

                </thead>

                <tbody>

                {
                    buckets.map((bucket, index) => (

                        <tr key={index}>

                            <td>{bucket.bucketName}</td>

                            <td>{bucket.region}</td>

                            <td>

                                <span
                                    className={
                                        bucket.encrypted
                                            ? "badge success-bg"
                                            : "badge danger-bg"
                                    }
                                >

                                    {
                                        bucket.encrypted
                                            ? "Yes"
                                            : "No"
                                    }

                                </span>

                            </td>

                            <td>

                                <span
                                    className={
                                        bucket.publicAccess
                                            ? "badge danger-bg"
                                            : "badge success-bg"
                                    }
                                >

                                    {
                                        bucket.publicAccess
                                            ? "Public"
                                            : "Private"
                                    }

                                </span>

                            </td>

                        </tr>
                    ))
                }

                </tbody>

            </table>

        </div>
    );
}

export default S3Table;