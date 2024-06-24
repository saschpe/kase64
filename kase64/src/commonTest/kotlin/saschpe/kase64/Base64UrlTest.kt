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

class Base64UrlTest {
    @Test
    fun byteArray_base64UrlDecoded() {
        assertEquals("Hello, world!", "SGVsbG8sIHdvcmxkIQ==".encodeToByteArray().base64UrlDecoded)
        assertContentEquals(
            byteArrayOf(
                -94, 124, -26, -112, -72, -84, 16, 11, 67, -45, 107, 38, -99, 79, 62, -49, 83, 26, -85, -70, -122, 53,
                67, 42, -94, -87, 61, -74, 66, 0, 80, -125, -17, -11, -125, 63, 109, -15, 56, -95, -33, 18, 110, 47,
                47, -20, -72, -34, 53, -69, 49, -45, 54, 53, -21, 43, 9, -84, -125, 72, -61, 76, 31, -46,
            ),
            "onzmkLisEAtD02smnU8-z1Maq7qGNUMqoqk9tkIAUIPv9YM_bfE4od8Sbi8v7LjeNbsx0zY16ysJrINIw0wf0g==".base64UrlDecodedBytes,
        )
    }

    @Test
    fun byteArray_base64UrlEncoded() {
        assertEquals(
            "xvrp9DBWlei2mG0ov9MN-A", // value1
            byteArrayOf(-58, -6, -23, -12, 48, 86, -107, -24, -74, -104, 109, 40, -65, -45, 13, -8).base64UrlEncoded,
        )
        assertEquals(
            "IkYJxF8nIQD9RY7Yk6r26A", // value222
            byteArrayOf(34, 70, 9, -60, 95, 39, 33, 0, -3, 69, -114, -40, -109, -86, -10, -24).base64UrlEncoded,
        )
        assertEquals(
            "U0GeVBi2dNcdL2IO0nJo5Q", // value555
            byteArrayOf(83, 65, -98, 84, 24, -74, 116, -41, 29, 47, 98, 14, -46, 114, 104, -27).base64UrlEncoded,
        )
    }

    @Test
    fun string_base64UrlDecoded() {
        assertEquals("word", "d29yZA==".base64UrlDecoded)
        assertEquals("Word", "V29yZA==".base64UrlDecoded)
        assertEquals("Hello", "SGVsbG8=".base64UrlDecoded)
        assertEquals("World!", "V29ybGQh".base64UrlDecoded)
        assertEquals("Hello, world!", "SGVsbG8sIHdvcmxkIQ".base64UrlDecoded)
        assertEquals("Hello, world!", "SGVsbG8sIHdvcmxkIQ==".base64UrlDecoded)
        assertEquals(
            Encoding.Standard.alphabet,
            "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw==".base64UrlDecoded,
        )
        assertEquals("Salt", "U2FsdA==".base64UrlDecoded)
        assertEquals("Pepper", "UGVwcGVy".base64UrlDecoded)
        assertEquals("abcd", "YWJjZA".base64UrlDecoded)
        assertEquals(
            "1234567890-=!@#\$%^&*()_+qwertyuiop[];'\\,./?><|\":}{P`~",
            "MTIzNDU2Nzg5MC09IUAjJCVeJiooKV8rcXdlcnR5dWlvcFtdOydcLC4vPz48fCI6fXtQYH4".base64UrlDecoded,
        )
        assertEquals("saschpe", "c2FzY2hwZQ".base64UrlDecoded)
        assertEquals(
            """{"aud":"ae.rain.com","exp":1713261313,"iat":1713259513,"iss":"ae.rain.com","rain:jurisdiction":"uae","sub":"58490195-01b9-4dcf-ae28-ae0b20f8fbda"}""",
            "eyJhdWQiOiJhZS5yYWluLmNvbSIsImV4cCI6MTcxMzI2MTMxMywiaWF0IjoxNzEzMjU5NTEzLCJpc3MiOiJhZS5yYWluLmNvbSIsInJhaW46anVyaXNkaWN0aW9uIjoidWFlIiwic3ViIjoiNTg0OTAxOTUtMDFiOS00ZGNmLWFlMjgtYWUwYjIwZjhmYmRhIn0".base64UrlDecoded,
        )
    }

    @Test
    fun string_base64UrlEncoded() {
        assertEquals("d29yZA", "word".base64UrlEncoded)
        assertEquals("V29yZA", "Word".base64UrlEncoded)
        assertEquals("SGVsbG8", "Hello".base64UrlEncoded)
        assertEquals("V29ybGQh", "World!".base64UrlEncoded)
        assertEquals("SGVsbG8sIHdvcmxkIQ", "Hello, world!".base64UrlEncoded)
        assertEquals("SGVsbG8sIHdvcmxkIQ", "Hello, world!".encodeToByteArray().base64UrlEncoded)
        assertEquals(
            "QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVphYmNkZWZnaGlqa2xtbm9wcXJzdHV2d3h5ejAxMjM0NTY3ODkrLw",
            Encoding.Standard.alphabet.base64UrlEncoded,
        )
        assertEquals("U2FsdA", "Salt".base64UrlEncoded)
        assertEquals("UGVwcGVy", "Pepper".base64UrlEncoded)
        assertEquals("YWJjZA", "abcd".base64UrlEncoded)
        assertEquals(
            "MTIzNDU2Nzg5MC09IUAjJCVeJiooKV8rcXdlcnR5dWlvcFtdOydcLC4vPz48fCI6fXtQYH4",
            "1234567890-=!@#\$%^&*()_+qwertyuiop[];'\\,./?><|\":}{P`~".base64UrlEncoded,
        )
        assertEquals("c2FzY2hwZQ", "saschpe".base64UrlEncoded)
    }

    @Test
    fun string_roundTrip() =
        assertEquals(Encoding.UrlSafe.alphabet, Encoding.UrlSafe.alphabet.base64UrlEncoded.base64UrlDecoded)
}
