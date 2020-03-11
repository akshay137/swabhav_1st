package web

import (
	"encoding/json"
	"net/http"
)

// HTTPError error struct for sending error message
type HTTPError struct {
	Msg string `json:"message"`
}

// NewHTTPError returns new web error
func NewHTTPError(msg string) *HTTPError {
	return &HTTPError{
		Msg: msg,
	}
}

func (we *HTTPError) Error() string {
	return we.Msg
}

// WriteHTTPError writes error message for http
func WriteHTTPError(statusCode int, msg string, w *http.ResponseWriter) {
	(*w).Header().Set("Content-Type", "application/json")
	(*w).WriteHeader(statusCode)
	json.NewEncoder(*w).Encode(NewHTTPError(msg))
}

// EncodeJSONObject writes `out` as json object to Writer
func EncodeJSONObject(out interface{}, w *http.ResponseWriter) error {
	(*w).Header().Set("Content-Type", "application/json")
	(*w).WriteHeader(http.StatusOK)
	err := json.NewEncoder(*w).Encode(out)
	if err != nil {
		return err
	}
	return nil
}
