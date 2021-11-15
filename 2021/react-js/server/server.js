const express = require('express');
const path = require('path');
const cors = require('cors');
const logger = require('./logger');
const app = express();
const { createProxyMiddleware } = require('http-proxy-middleware');
const BUILD_DIR = path.join(__dirname, '/../build');
const PORT = process.env.SERVER_PORT || 3000;
const REPORT_API_ENDPOINT = process.env.REPORT_API_ENDPOINT;
logger.info(`REPORT_API_ENDPOINT: ${REPORT_API_ENDPOINT}`);
const MONITOR_API_ENDPOINT = process.env.MONITOR_API_ENDPOINT;
logger.info(`MONITOR_API_ENDPOINT: ${MONITOR_API_ENDPOINT}`);

const COUNTER_API_ENDPOINT = process.env.COUNTER_API_ENDPOINT;
logger.info(`COUNTER_API_ENDPOINT: ${COUNTER_API_ENDPOINT}`);

const DEDUP_API_ENDPOINT = process.env.DEDUP_API_ENDPOINT;
logger.info(`DEDUP_API_ENDPOINT: ${DEDUP_API_ENDPOINT}`);


app.use(cors());

// app.use(['/report/*','/reports'], createProxyMiddleware({ 
//     target: REPORT_API_ENDPOINT, 
//     changeOrigin: true
// }));

// app.use(['/get_statistics','/consumer_groups','/consumer_groups_detail'], createProxyMiddleware({ 
//     target: MONITOR_API_ENDPOINT, 
//     changeOrigin: true
// }));

// app.use(['/countstart'], createProxyMiddleware({ 
//     target: COUNTER_API_ENDPOINT, 
//     changeOrigin: true
// }));

// app.use(['/dedupstart'], createProxyMiddleware({ 
//     target: DEDUP_API_ENDPOINT, 
//     changeOrigin: true
// }));

app.use(express.static(BUILD_DIR));
app.use(express.json());     
app.use(express.urlencoded());
app.listen(PORT, () => {
    logger.info(`### App started on port ${PORT} ###`);
});
