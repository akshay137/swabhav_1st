package bookmark

import (
	"fmt"
	"strings"
)

// Category stores category for bookmark
type Category struct {
	ID  int64
	Cat string `gorm:"type:varchar(50);unique"`
}

func getOrCreateCategory(name string) *Category {
	catName := strings.ToLower(name)
	cat := &Category{}
	db.First(cat, "cat=?", catName)
	fmt.Println("cat", cat)
	if cat.ID == 0 {
		return NewCategory(catName)
	}
	return cat
}

// NewCategory returns new Category object
func NewCategory(name string) *Category {
	catName := strings.ToLower(name)
	cat := &Category{Cat: catName}
	db.Create(cat)
	return cat
}

// GetCategoryByID returns category by id
func GetCategoryByID(id string) *Category {
	return nil
}

// GetAllCategories returns all categories
func GetAllCategories() []Category {
	cats := []Category{}
	db.Find(&cats)
	return cats
}

// UpdateCategory update category by id
func UpdateCategory(id int, category Category) {}

// DeleteCategoryByID deletes category by id
func DeleteCategoryByID(id int64) {}
