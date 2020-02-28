package bookmark

import (
	"io"

	"github.com/jinzhu/gorm"
)

// BMService stores database connection
type BMService struct {
	db *gorm.DB
}

// BMExport provides interface for exporting bookmark
// to various formats
type BMExport interface {
	ToExportString(bm *BookMark) string
}

// NewBMService returns new instance of BMService
func NewBMService(db *gorm.DB) *BMService {
	bms := &BMService{
		db: db,
	}
	db.AutoMigrate(&BookMark{})
	return bms
}

// AddNewBookMark adds new bookmark to database
func (bms *BMService) AddNewBookMark(
	name, url string, userID uint) (*BookMark, error) {
	bm := newBookMark(name, url, userID)
	bms.db.Save(bm)
	if bms.db.Error != nil {
		return nil, bms.db.Error
	}
	return bm, nil
}

// GetAllBookMarks returns all bookmarks associated with
// user
func (bms *BMService) GetAllBookMarks(userID uint) []BookMark {
	bm := []BookMark{}
	bms.db.Find(&bm, "user_id=?", userID)
	if bms.db.Error != nil {
		return nil
	}
	return bm
}

// GetBookMarkById return bookmark by id
func (bms *BMService) GetBookMarkById(id uint, uid uint) *BookMark {
	bm := &BookMark{}
	bms.db.First(bm, "id=? and user_id=?", id, uid)
	if bm.ID == 0 {
		return nil
	}
	return bm
}

// DeleteBookMarkById deletes bookmark by its id
func (bms *BMService) DeleteBookMarkById(id uint, uid uint) error {
	bms.db.Delete(&BookMark{}, "id=? and user_id=?",
		id, uid)
	if bms.db.Error != nil {
		return bms.db.Error
	}
	return nil
}

// UpdateBookMarkById update bookmark data
func (bms *BMService) UpdateBookMarkById(id uint, bm *BookMark) error {
	bms.db.Save(bm)
	if bms.db.Error != nil {
		return bms.db.Error
	}
	return nil
}

// ExportBookMarks exports bookmarks to provided format
func (bms *BMService) ExportBookMarks(bm []BookMark, w io.Writer, exp BMExport) error {
	for _, b := range bm {
		_, err := w.Write([]byte(exp.ToExportString(&b)))
		if err != nil {
			return err
		}
	}
	return nil
}
