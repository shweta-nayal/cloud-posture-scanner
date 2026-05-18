import axios from "axios";

const BASE_URL = "http://localhost:8080";

export const runScan = () =>
    axios.post(`${BASE_URL}/scan`);

export const getInstances = () =>
    axios.get(`${BASE_URL}/instances`);

export const getBuckets = () =>
    axios.get(`${BASE_URL}/buckets`);

export const getCISResults = () =>
    axios.get(`${BASE_URL}/cis-results`);