package main

import (
	"bookmarkstore_app/bookmark"
	"bufio"
	"fmt"
	"os"
	"strings"
)

type html struct{}

var bsvc *bookmark.BMService

func (h *html) GetExportString(bm *bookmark.BookMark) string {
	return fmt.Sprintf("<a href=\"%s\">%s</a><br/>\n",
		bm.URL, bm.Name)
}

func main() {
	fmt.Println("Type help to see supported commands")
	bsvc = bookmark.GetBookMarkService()
	defer bsvc.CleanUp()
	in := bufio.NewReader(os.Stdin)
	for true {
		fmt.Print("bookmarkstore $ ")
		cmd, err := in.ReadString('\n')
		if err != nil {
			fmt.Println("Error", err.Error())
		}
		if strings.HasPrefix(cmd, "exit") {
			break
		} else if strings.HasPrefix(cmd, "help") {
			printHelp()
		} else if strings.HasPrefix(cmd, "add") {
			add(strings.Split(cmd, " ")[1:])
		} else if strings.HasPrefix(cmd, "print-all") {
			printAll()
		} else if strings.HasPrefix(cmd, "delete") {
			id := strings.Split(cmd, " ")[1]
			id = strings.Replace(id, "\n", "", -1)
			delete(id)
		} else if strings.HasPrefix(cmd, "get") {
			id := strings.Split(cmd, " ")[1]
			id = strings.Replace(id, "\n", "", -1)
			bm, err := get(id)
			if err != nil {
				fmt.Println("No bookmark by given id", err.Error())
			} else {
				if bm != nil {
					bsvc.PrintBookMark(bm)
				} else {
					fmt.Println("No such bookmark")
				}
			}
		} else if strings.HasPrefix(cmd, "export") {
			export(strings.Split(
				strings.Replace(cmd, "\n", "", -1), " ")[1])
		} else if strings.HasPrefix(cmd, "update") {
			tokens := strings.Split(cmd, " ")[1:]
			if len(tokens) < 3 {
				fmt.Println("usage update <name/url> <data>")
				continue
			}
			switch strings.ToLower(tokens[0]) {
			case "name":
				updateName(tokens[1], tokens[2])
			case "url":
				updateUrl(tokens[1], tokens[2])
			}
		} else {
			fmt.Println("Unsupported command.", cmd)
			fmt.Println("type help to see supported command")
		}
		// fmt.Println("command", cmd)
	}
}

func printHelp() {
	fmt.Println("[help]")
	fmt.Println("---------------")
	fmt.Println("supported commands")
	fmt.Println("add <bookmark_name> <url> : add new bookmark")
	fmt.Println("delete <bookmark_id> : deletes bookmark")
	fmt.Println("print-all : displays all bookmarks")
	fmt.Println("get <bookamrk_id> : display bookmark by id")
	fmt.Println("export : export bookmarks to html")
	fmt.Println("exit : quit the programm")
	fmt.Println("---------------")
}

func add(data []string) error {
	fmt.Println("[add]")
	if len(data) < 2 {
		fmt.Println("usage: add <name> <url>")
		return nil
	}
	// fmt.Println("adding", data)
	bsvc.AddNewBookmark(data[0], data[1], "")
	return nil
}

func delete(id string) error {
	fmt.Println("[delete]")
	bsvc.DeleteBookMarkByID(id)
	return nil
}

func get(id string) (*bookmark.BookMark, error) {
	fmt.Println("[get]")
	return bsvc.GetBookMarkByID(id)
}

func updateName(id, name string) error {
	fmt.Println("[update-name]")
	return bsvc.UpdateBookMarkName(id,
		strings.ReplaceAll(name, "\n", ""))
}

func updateUrl(id, url string) error {
	fmt.Println("[update-url]")
	return bsvc.UpdateBookMarkURL(id,
		strings.ReplaceAll(url, "\n", ""))
}

func printAll() {
	fmt.Println("[print-all]")
	bs := bsvc.GetAllBookMarks()
	if len(bs) == 0 {
		fmt.Println("No bookmarks added yet")
		return
	}
	for _, bm := range bs {
		bsvc.PrintBookMark(&bm)
	}
}

func export(filename string) error {
	fmt.Println("[export]")
	bs := bsvc.GetAllBookMarks()
	f, err := os.Create(filename)
	defer f.Close()
	if err != nil {
		return err
	}
	err = bsvc.ExportTo(bs, &html{}, f)
	if err != nil {
		return err
	}
	return nil
}
