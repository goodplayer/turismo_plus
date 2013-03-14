/*
 * Copyright (c) 2013 goodplayer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.moetang.turismo_plus.util;

//depend on the last action in Befores
public interface BeforeChain {
	public void doNext();
	// skip all befores
	public void stopBefores();
	// skip all befores and action
	public void stopAction();
	// skip all - stop processing
	public void stopAll();
}
