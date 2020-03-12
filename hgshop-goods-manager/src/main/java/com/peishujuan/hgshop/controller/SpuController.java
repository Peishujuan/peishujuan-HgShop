package com.peishujuan.hgshop.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.peishujuan.hgshop.pojo.Brand;
import com.peishujuan.hgshop.pojo.Spu;
import com.peishujuan.hgshop.pojo.SpuVo;
import com.peishujuan.hgshop.service.BrandService;
import com.peishujuan.hgshop.service.SpuService;

@Controller
@RequestMapping("goods")
public class SpuController {

	@Reference
	SpuService spuService;
	
	@Reference
	BrandService brandService;
	/**
	 * 
	 * @param request
	 * @param page  页码
	 * @param spuVo 查询条件
	 * @return
	 */
	@RequestMapping("list")
	public String list(HttpServletRequest request,@RequestParam(defaultValue="1")int pageNum,SpuVo vo) {
		PageInfo<Spu> listSpu = spuService.listSpu(pageNum, vo);
		request.setAttribute("pageInfo", listSpu);
		return "goods/list";
	}
	
	@RequestMapping("toadd")
	public String add(HttpServletRequest request ) {
		List<Brand> allBrands = brandService.getAllBrands();
		request.setAttribute("brands", allBrands);
		return "goods/add";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String add(HttpServletRequest request,Spu spu,
			@RequestParam(value="file") MultipartFile file) throws IllegalStateException, IOException {
		
		
		 ////返回的上传文件存放的物理地址
		String filePath=processFile(file);
		////可以根据 这个地址下载
		spu.setSmallPic(filePath);
		
		int result = spuService.addSpu(spu);
		
		return result>0?"success":"failed";
	}
	
	/**
	  *  上传文件
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	private String processFile(MultipartFile file) throws IllegalStateException, IOException {

		// 原来的文件名称
		System.out.println("file.isEmpty() :" + file.isEmpty()  );
		System.out.println("file.name :" + file.getOriginalFilename());
		
		if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
			return "";
		}
			
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
		String path = "f:/pic/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
		File distFile = new File( destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		return destFileName.substring(7);
	}
	
	/**
	 * 
	 * @param response
	 * @param file
	 * @throws FileNotFoundException 
	 */
	@RequestMapping("down")
	public void downLoad(HttpServletResponse response, String filename) throws FileNotFoundException {
		 /* // 下载本地文件
	    String fileName = "Operator.doc".toString(); // 文件的默认保存名
*/	    // 读到流中
	    InputStream inStream = new FileInputStream("F:\\pic\\"+filename);// 文件的存放路径
	    // 设置输出的格式
	    response.reset();
	    response.setContentType("bin");
	    response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
	   
	    // 循环取出流中的数据
	    byte[] b = new byte[1024];
	    int len;
	    try {
	      while ((len = inStream.read(b)) > 0)
	        response.getOutputStream().write(b, 0, len);
	      inStream.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	
	}
}
