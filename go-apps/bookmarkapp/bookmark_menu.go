package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"

	"github.com/akshay137/bookmarkapp/bookmark"
)

// HTMLExport implements BMExport
type HTMLExport struct{}

// ToExportString returns html string
func (he *HTMLExport) ToExportString(bm *bookmark.BookMark) string {
	return fmt.Sprintf(
		"<a id=\"%v\" href=\"%s\">%s</a><br/>\n",
		bm.ID, bm.URL, bm.Name)
}

func showBookMarkMenu(uid uint, bmsvc *bookmark.BMService) {
	reader := bufio.NewReader(os.Stdin)
	for true {
		fmt.Println("1. Add bookmark")
		fmt.Println("2. Update bookmark Name")
		fmt.Println("3. Update bookmark Url")
		fmt.Println("4. Delete bookmark")
		fmt.Println("5. View bookmark")
		fmt.Println("6. Export")
		fmt.Println("7. Logout")
		opt, err := reader.ReadString('\n')
		if err != nil {
			fmt.Println("Error reading from stdin")
			continue
		}
		opt = opt[:len(opt)-1]
		i, err := strconv.Atoi(opt)
		if err != nil {
			fmt.Println("Please select valid option")
			continue
		}
		switch i {
		case 1:
			addBookmark(reader, bmsvc, uid)
		case 2:
			updateBookmarkName(reader, bmsvc, uid)
		case 3:
			updateBookmarkUrl(reader, bmsvc, uid)
		case 4:
			deleteBookmark(reader, bmsvc, uid)
		case 5:
			viewBookmark(reader, bmsvc, uid)
		case 6:
			exportBookmarks(reader, bmsvc, uid)
		case 7:
			return
		default:
			fmt.Println("Please select valid option")
		}
	}
	return
}

func addBookmark(reader *bufio.Reader, bmsvc *bookmark.BMService, uid uint) {
	fmt.Println("Enter name of bookmark")
	bname, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println("Error reading your input")
		return
	}
	fmt.Println("Enter url of bookmark")
	burl, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println("Error reading your input")
		return
	}
	bname = bname[:len(bname)-1]
	burl = burl[:len(burl)-1]
	_, err = bmsvc.AddNewBookMark(bname, burl, uid)
	if err != nil {
		fmt.Println("Erorr occured while adding new bookmark")
		return
	}
	fmt.Println("Bookmark added")
}

func updateBookmarkName(reader *bufio.Reader, bmsvc *bookmark.BMService, uid uint) {
	fmt.Println("Enter id")
	id, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println("Error reading")
		return
	}
	id = id[:len(id)-1]
	fmt.Println("Enter name")
	bname, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println()
		return
	}
	i, _ := strconv.Atoi(id)
	fmt.Println("id", i)
	bm := bmsvc.GetBookMarkById(uint(i), uid)
	if bm == nil {
		fmt.Println("No such bookmark or you are not allowed to update this")
		return
	}
	bm.Name = bname[:len(bname)-1]
	bmsvc.UpdateBookMarkById(uint(i), bm)
	fmt.Println(bm.Name)
}

func updateBookmarkUrl(reader *bufio.Reader, bmsvc *bookmark.BMService, uid uint) {
	fmt.Println("Enter id")
	id, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println("Error reading")
		return
	}
	id = id[:len(id)-1]
	fmt.Println("Enter url")
	burl, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println()
		return
	}
	i, _ := strconv.Atoi(id)
	fmt.Println("id", i)
	bm := bmsvc.GetBookMarkById(uint(i), uid)
	if bm == nil {
		fmt.Println("No such bookmark or you are not allowed to update this")
		return
	}
	bm.URL = burl[:len(burl)-1]
	bmsvc.UpdateBookMarkById(uint(i), bm)
	fmt.Println(bm.URL)
}

func deleteBookmark(reader *bufio.Reader, bmsvc *bookmark.BMService, uid uint) {
	fmt.Println("Enter bookmark id")
	id, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println("Error reading")
		return
	}
	i, _ := strconv.Atoi(id[:len(id)-1])
	bmsvc.DeleteBookMarkById(uint(i), uid)
}

func viewBookmark(reader *bufio.Reader, bmsvc *bookmark.BMService, uid uint) {
	bms := bmsvc.GetAllBookMarks(uid)
	if bms == nil || len(bms) == 0 {
		fmt.Println("No bookmarks yet")
	}
	for _, bm := range bms {
		// fmt.Println(bm.Name, bm.URL)
		fmt.Println("ID:", bm.ID)
		fmt.Println("Name:", bm.Name)
		fmt.Println("URL:", bm.URL)
		fmt.Println("")
	}
}

func exportBookmarks(reader *bufio.Reader, bmsvc *bookmark.BMService, uid uint) {
	fmt.Println("Enter file name")
	fname, err := reader.ReadString('\n')
	if err != nil {
		fmt.Println("Error reading")
		return
	}
	fname = fname[:len(fname)-1]
	f, err := os.Create(fname)
	if err != nil {
		fmt.Println("Error opening file")
		return
	}
	defer f.Close()
	bms := bmsvc.GetAllBookMarks(uid)
	bmsvc.ExportBookMarks(bms, f, &HTMLExport{})
}
