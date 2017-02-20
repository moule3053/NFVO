/*
 * Copyright (c) 2016 Open Baton (http://openbaton.org)
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

package org.openbaton.nfvo.api.admin;

import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.Set;
import org.openbaton.exceptions.AlreadyExistingException;
import org.openbaton.nfvo.core.interfaces.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/** Created by rvl on 19.10.16. */
@RestController
@RequestMapping("/api/v1/plugins")
public class RestPlugin {

  @Autowired PluginManager pluginManager;

  @ApiOperation(
    value = "Download a plugin from the Open Baton Marketplace",
    notes = "The Plugin can be downloaded by specifying the type, name and version in the URL"
  )
  @RequestMapping(value = "{type}/{name}/{version:.+}", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public void downloadPlugin(
      @PathVariable String type, @PathVariable String name, @PathVariable String version)
      throws IOException, AlreadyExistingException {

    pluginManager.downloadPlugin(type, name, version);
  }

  @ApiOperation(value = "Retrieve all installed Vim Drivers", notes = "")
  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody Set<String> getListVimDrivers() throws IOException {
    return pluginManager.listInstalledVimDrivers();
  }
}
