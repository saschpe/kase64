/*
 * Copyright 2022 Sascha Peilicke <sascha@peilicke.de>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package saschpe.kase64

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Base64Test {
    @Test
    fun byteArray_base64Decoded() {
        assertEquals("Hello, world!", "SGVsbG8sIHdvcmxkIQ==".encodeToByteArray().base64Decoded)
    }

    @Test
    fun byteArray_base64Encoded() {
        assertEquals(
            "xvrp9DBWlei2mG0ov9MN+A==", // value1
            byteArrayOf(-58, -6, -23, -12, 48, 86, -107, -24, -74, -104, 109, 40, -65, -45, 13, -8).base64Encoded
        )
        assertEquals(
            "IkYJxF8nIQD9RY7Yk6r26A==", // value222
            byteArrayOf(34, 70, 9, -60, 95, 39, 33, 0, -3, 69, -114, -40, -109, -86, -10, -24).base64Encoded
        )
        assertEquals(
            "U0GeVBi2dNcdL2IO0nJo5Q==", // value555
            byteArrayOf(83, 65, -98, 84, 24, -74, 116, -41, 29, 47, 98, 14, -46, 114, 104, -27).base64Encoded
        )
    }

    @Test
    fun byteArray_asCharArray() {
        assertContentEquals(
            charArrayOf('"', 'F', '	', 'ￄ', '_', '\'', '!', ' ', '�', 'E', 'ﾎ', '￘', 'ﾓ', 'ﾪ', '￶', '￨'),
            byteArrayOf(34, 70, 9, -60, 95, 39, 33, 0, -3, 69, -114, -40, -109, -86, -10, -24).asCharArray()
        )
    }

    @Test
    fun string_base64Decoded() {
        assertEquals("word", "d29yZA==".base64Decoded)
        assertEquals("Word", "V29yZA==".base64Decoded)
        assertEquals("Hello", "SGVsbG8=".base64Decoded)
        assertEquals("World!", "V29ybGQh".base64Decoded)
        assertEquals("Hello, world!", "SGVsbG8sIHdvcmxkIQ==".base64Decoded)
        assertEquals(
            BASE64_SET,
            "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==".base64Decoded
        )
    }

    @Test
    fun string_base64Encoded() {
        assertEquals("d29yZA==", "word".base64Encoded)
        assertEquals("V29yZA==", "Word".base64Encoded)
        assertEquals("SGVsbG8=", "Hello".base64Encoded)
        assertEquals("V29ybGQh", "World!".base64Encoded)
        assertEquals("SGVsbG8sIHdvcmxkIQ==", "Hello, world!".base64Encoded)
        assertEquals("SGVsbG8sIHdvcmxkIQ==", "Hello, world!".encodeToByteArray().base64Encoded)
        assertEquals(
            "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==",
            BASE64_SET.base64Encoded
        )
    }

    @Test
    fun string_roundTrip() {
        assertEquals(BASE64_SET, BASE64_SET.base64Encoded.base64Decoded)
    }
}
