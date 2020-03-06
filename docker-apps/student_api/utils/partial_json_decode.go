package utils

import (
	"encoding/json"
	"fmt"
	"reflect"
	"strings"
)

// DecodePartialJSON decodes json partially if errors or completely
func DecodePartialJSON(data []byte, out interface{}) error {
	// unmarshal json
	var jsonMap map[string]json.RawMessage
	err := json.Unmarshal(data, &jsonMap)
	if err != nil {
		return err
	}

	// iterate over fields of struct
	v := reflect.Indirect(reflect.ValueOf(out))
	fieldCount := v.NumField()
	for i := 0; i < fieldCount; i++ {
		sf := v.Type().Field(i)
		field := v.Field(i)
		jsonName := getJsonName(sf.Tag.Get("json"))
		if value, ok := jsonMap[jsonName]; ok {
			// set value to field
			if !field.IsValid() {
				continue
			}
			err := unmarshal(&field, value)
			if err != nil {
				continue
				// return err
			}
		}
	}
	return nil
}

func getJsonName(tag string) string {
	t := strings.Split(tag, ",")
	if len(t) > 0 {
		return t[0]
	}
	return ""
}

func unmarshal(f *reflect.Value, val json.RawMessage) error {
	if !f.IsValid() {
		return fmt.Errorf("Invalid field")
	}
	if !f.CanSet() {
		return fmt.Errorf("Can't set value")
	}
	fmt.Println("kind", f.Kind())
	switch f.Kind() {
	case reflect.Int,
		reflect.Int8,
		reflect.Int16,
		reflect.Int32,
		reflect.Int64:
		var i int64
		err := json.Unmarshal(val, &i)
		if err != nil {
			return err
		}
		f.SetInt(i)
		break

	case reflect.Uint,
		reflect.Uint8,
		reflect.Uint16,
		reflect.Uint32,
		reflect.Uint64:
		var ui uint64
		err := json.Unmarshal(val, &ui)
		if err != nil {
			return err
		}
		f.SetUint(ui)
		break

	case reflect.Float32, reflect.Float64:
		var f64 float64
		err := json.Unmarshal(val, &f64)
		if err != nil {
			return err
		}
		f.SetFloat(f64)
		break

	case reflect.String:
		var s string
		err := json.Unmarshal(val, &s)
		if err != nil {
			return err
		}
		f.SetString(s)
		break

	case reflect.Bool:
		var b bool
		err := json.Unmarshal(val, &b)
		if err != nil {
			return err
		}
		f.SetBool(b)
		break
	}
	return nil
}
