import "../styles/Table.css";

function EC2Table({ instances }) {

    return (

        <div className="section">

            <h2>EC2 Instances</h2>

            <p>
                Discovered compute resources across regions
            </p>

            <table>

                <thead>

                <tr>

                    <th>Instance ID</th>
                    <th>Type</th>
                    <th>Region</th>
                    <th>Public IP</th>
                    <th>Security Group</th>

                </tr>

                </thead>

                <tbody>

                {
                    instances.map((instance, index) => (

                        <tr key={index}>

                            <td>{instance.instanceId}</td>

                            <td>{instance.instanceType}</td>

                            <td>{instance.region}</td>

                            <td>{instance.publicIp}</td>

                            <td>{instance.securityGroup}</td>

                        </tr>
                    ))
                }

                </tbody>

            </table>

        </div>
    );
}

export default EC2Table;