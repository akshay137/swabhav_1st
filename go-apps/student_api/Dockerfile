FROM golang

WORKDIR /studentapi

COPY ./ /studentapi

RUN go mod download

RUN go build -o studentapi

ENTRYPOINT ["/studentapi/studentapi"]