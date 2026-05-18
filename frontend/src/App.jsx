import { useEffect, useState } from "react";

import {
    runScan,
    getInstances,
    getBuckets,
    getCISResults
} from "./services/api";

import Header from "./components/Header";
import SummaryCards from "./components/SummaryCards";
import EC2Table from "./components/EC2Table";
import S3Table from "./components/S3Table";
import CISResults from "./components/CISResults";

import "./styles/App.css";

function App() {

    const [instances, setInstances] = useState([]);

    const [buckets, setBuckets] = useState([]);

    const [cisResults, setCisResults] = useState([]);

    const [loading, setLoading] = useState(false);

    const fetchData = async () => {

        try {

            setLoading(true);

            await runScan();

            const instanceRes =
                await getInstances();

            const bucketRes =
                await getBuckets();

            const cisRes =
                await getCISResults();

            setInstances(instanceRes.data);

            setBuckets(bucketRes.data);

            setCisResults(cisRes.data);

        } catch (error) {

            console.log(error);

        } finally {

            setLoading(false);
        }
    };

    useEffect(() => {

        fetchData();

    }, []);

    const passedChecks =
        cisResults.filter(
            (item) => item.status === "PASS"
        ).length;

    return (

        <div className="container">

            <Header
                loading={loading}
                onScan={fetchData}
            />

            <SummaryCards
                instances={instances.length}
                buckets={buckets.length}
                checks={cisResults.length}
                passedChecks={passedChecks}
            />

            <EC2Table instances={instances} />

            <S3Table buckets={buckets} />

            <CISResults results={cisResults} />

        </div>
    );
}

export default App;