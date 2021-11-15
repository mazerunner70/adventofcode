FROM node:14

RUN mkdir -p /entellect-report-viewer
WORKDIR /entellect-report-viewer

COPY package*.json ./

RUN npm install

COPY ./ ./
RUN npm run build

CMD npm run server \
	fi

EXPOSE 3000