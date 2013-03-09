/*
 * Copyright (c) 2011 Adrian Fernandez
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

package com.ghosthack.turismo.resolver;

import com.ghosthack.turismo.Resolver;
import com.ghosthack.turismo.action.ActionException;
import com.ghosthack.turismo.servlet.Env;

public abstract class MethodPathResolver implements Resolver {

    private static final String UNDEFINED_PATH = "Undefined path";

    @Override
    public Runnable resolve() throws ActionException {
        String path = extractPath();
        String method = Env.req().getMethod();
        Runnable route = resolve(method, path);
        return route;
    }

    protected abstract Runnable resolve(String method, String path);

    private String extractPath() throws ActionException {
        String path = Env.req().getPathInfo();
        if (path == null) {
            path = Env.req().getServletPath();
            if (path == null) {
                throw new ActionException(UNDEFINED_PATH);
            }
        }
        return path;
    }

}
