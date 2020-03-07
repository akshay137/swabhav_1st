FROM node:latest as node
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
# RUN ng build --prod=true
RUN npm run build -- --prod

FROM nginx:alpine
COPY --from=node /app/dist/student-spa /usr/share/nginx/html
COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf