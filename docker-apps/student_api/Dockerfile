FROM golang

WORKDIR /studentapi2

COPY go.mod .
COPY go.sum .
RUN go mod download

COPY ./ /studentapi2

RUN go build -o studentapi2

ENTRYPOINT ["/studentapi2/studentapi2"]