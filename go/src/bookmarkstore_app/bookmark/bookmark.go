package bookmark

import "fmt"

// BookMark stores bookmark detail
type BookMark struct {
	ID   int64
	URL  string `gorm:"type:varchar(500);unique"`
	Name string `gorm:"type:varchar(50)"`
	// Cat  Category `gorm:"foreignkey:CID;asscoiation_foreignkye:ID"`
	// CID  int64
}

// NewBookMark returns a new bookmark object
func NewBookMark(name, url, category string) *BookMark {
	b := &BookMark{}
	db.Find(b, "url=?", url)
	if b.ID != 0 {
		return b
	}
	// cat := getOrCreateCategory(category)
	bookmark := &BookMark{
		Name: name,
		URL:  url,
		// Cat:  *cat,
		// CID:  cat.ID,
	}
	db.Create(bookmark)
	// fmt.Println(bookmark)
	return bookmark
}

func getBookMarkByID(id int64) *BookMark {
	bm := &BookMark{}
	db.First(bm, "id=?", id)
	if bm.ID > 0 {
		return bm
	}
	return nil
}

func getBookMarkByCategory(cat int64) {}

func getAllBookMarks() []BookMark {
	bm := []BookMark{}
	db.Find(&bm)
	return bm
}

func updateBookMark(id int64, bookmark *BookMark) error {
	bm := getBookMarkByID(id)
	if bm == nil {
		return fmt.Errorf("No such bookmark")
	}
	bookmark.ID = id
	db.Save(bookmark)
	return nil
}

func deleteBookMarkByID(id int64) {
	db.Delete(&BookMark{}, "id=?", id)
}
