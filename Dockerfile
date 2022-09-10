FROM node:alpine
WORKDIR '/app'
RUN npm install
COPY . ./app 
CMD ["npm", "start"]
