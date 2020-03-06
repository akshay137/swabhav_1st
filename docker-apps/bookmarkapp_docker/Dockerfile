FROM golang

WORKDIR /bmapp

COPY . .

RUN go mod download

RUN go build -o bm .

ENTRYPOINT [ "/bmapp/bm" ]