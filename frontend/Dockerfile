FROM node:14-alpine as node
EXPOSE 4200
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

FROM nginx:alpine
COPY --from=node /app/dist/frontend /usr/share/nginx/html

