/*
 * Copyright (c) 2018. Alexander Dunn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tech.alexanderontest.guicefactory.infrastructure.pagefactory;

import org.openqa.selenium.WebElement;

/**
 * Interface for the support of PageBlock classes.
 * Implementations should use the PageFactory to proxy fields relative to the WebElement rootElement field.
 */
@FunctionalInterface
public interface Block {
    WebElement getRootElement();
}
