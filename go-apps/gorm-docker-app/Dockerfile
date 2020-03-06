FROM golang

WORKDIR /gormapp

COPY . .

RUN go mod download

RUN go build -o gormapp .

ENTRYPOINT [ "/gormapp/gormapp" ]