package bookmark

import (
	"fmt"
	"io"
	"strconv"

	"github.com/jinzhu/gorm"

	// load mysql driver
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

var (
	db      *gorm.DB
	dbError error
	svc     BMService = BMService{}
)

// BMExport interface for providing export logic
type BMExport interface {
	GetExportString(bm *BookMark) string
}

// BMService service
type BMService struct{}

// GetBookMarkService __
func GetBookMarkService() *BMService {
	return &svc
}

func init() {
	db, dbError = gorm.Open("mysql",
		"root:root@tcp(127.0.0.1:3306)/swabhav")
	if dbError != nil {
		fmt.Println(dbError.Error())
	}

	db.AutoMigrate(&BookMark{})
	db.AutoMigrate(&Category{})
}

// CleanUp close the connection to db and free any resources
func (bms *BMService) CleanUp() {
	db.Close()
}

// AddNewBookmark adds new bookmark
func (bms *BMService) AddNewBookmark(name, url, cat string) *BookMark {
	return NewBookMark(name, url, cat)
}

// PrintBookMark print details to stdout
func (bms *BMService) PrintBookMark(bm *BookMark) {
	fmt.Println("ID", bm.ID)
	fmt.Println("Name", bm.Name)
	fmt.Println("URL", bm.URL)
	// fmt.Println("Category", bm.Cat.Cat)
	// fmt.Println("Category", bm.CID)
}

// ExportTo export bookmarks to writer accroding
// to BMExport implementation
func (bms *BMService) ExportTo(bookmarks []BookMark, export BMExport,
	w io.Writer) error {
	for _, bm := range bookmarks {
		_, err := w.Write([]byte(export.GetExportString(&bm)))
		if err != nil {
			return err
		}
	}
	return nil
}

// DeleteBookMarkByID delete bookmark
func (bms *BMService) DeleteBookMarkByID(id string) error {
	ID, err := strconv.ParseInt(id, 10, 64)
	if err != nil {
		return err
	}
	deleteBookMarkByID(ID)
	return nil
}

// GetBookMarkByID get bookmark by id
func (bms *BMService) GetBookMarkByID(id string) (*BookMark, error) {
	ID, err := strconv.ParseInt(id, 10, 64)
	if err != nil {
		return nil, err
	}
	return getBookMarkByID(ID), nil
}

// GetAllBookMarks return all bookmarks
func (bms *BMService) GetAllBookMarks() []BookMark {
	return getAllBookMarks()
}

// UpdateBookMarkName update bookmark by id
func (bms *BMService) UpdateBookMarkName(id, name string) error {
	bm, err := bms.GetBookMarkByID(id)
	if bm == nil {
		return err
	}
	bm.Name = name
	return updateBookMark(bm.ID, bm)
}

// UpdateBookMarkURL updates url of bookmark
func (bms *BMService) UpdateBookMarkURL(id, url string) error {
	bm, err := bms.GetBookMarkByID(id)
	if bm == nil {
		return err
	}
	bm.URL = url
	return updateBookMark(bm.ID, bm)
}
