package model

// Contact stores contact info
type Contact struct {
	ID     int64
	Name   string `gorm:"type:varchar(100)"`
	Number string `gorm:"type:char(10)"`
}
