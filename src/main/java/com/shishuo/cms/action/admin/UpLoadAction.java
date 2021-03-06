/*
 * 
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *	 
 *		http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package com.shishuo.cms.action.admin;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 文件上传
 * 
 */
@Controller
@RequestMapping("/admin")
public class UpLoadAction {

	@RequestMapping(value = "upload.htm", method = RequestMethod.GET)
	public String upload() {
		return "admin/upload";
	}

	@ResponseBody
	@RequestMapping(value = "photo.htm", method = RequestMethod.POST)
	public ModelAndView handleRequest(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		if (this.checkPhotoFile(file)) {
			String filePathName = "";
			String fileName = file.getOriginalFilename();
			String filePath = filePathName + fileName;
			File source = new File(filePath.toString());
			file.transferTo(source);
		} else {
			String errorMessage = "上传的文件只能是jpg,png,gif的图片格式";
		}
		return null;
	}

	public boolean checkPhotoFile(MultipartFile file) {
		if (file.isEmpty()) {
			return false;
		}
		if (file.getContentType().equals("image/jpg")
				|| file.getContentType().equals("image/png")
				|| file.getContentType().equals("image/jpeg")
				|| file.getContentType().equals("image/gif")) {
			return true;
		} else {
			return false;
		}
	}

}
