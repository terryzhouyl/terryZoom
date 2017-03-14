package com.terry.controller.common;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.terry.controller.MyController;
import com.terry.util.FileUtil;
import com.terry.util.ImageUtil;


@Controller("toolFileController")
@RequestMapping(value = "/file")
public class FileController extends MyController {
	
	//多图片上传
	
	@RequestMapping(value = "/multi_upload")
	public ResponseEntity<String> multi_upload(HttpServletRequest request,
			@RequestParam("cmfile") CommonsMultipartFile[] cmfiles, Integer fsize,
			Integer imgWidth, HttpServletResponse response, Integer type) {
		
		String msg = "上传成功";
		Boolean success = true;
		
		String fileFolder = "upload/original";				
		StringBuilder filePath = new StringBuilder();				
		
		try{
			for(CommonsMultipartFile cmfile:cmfiles){			
				filePath.append(ImageUtil.uploadPicture(cmfile, fsize, fileFolder));				
				filePath.append(",");								
			}
			filePath.deleteCharAt(filePath.length()-1);
		}
		catch(Exception e){
			success = false;
			msg = "上传失败";
		}
		return renderData(success, msg, filePath.toString());
	}
	
	
	


	/**
	 * 下载文件
	 * 
	 * @param request
	 * @param response
	 * @param fileName
	 * @throws IOException
	 */
	@RequestMapping(value = "/download")
	public void download(HttpServletRequest request,
			HttpServletResponse response, String fileName) throws IOException {
		String nowPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ File.separator
				+ "upload"
				+ File.separator
				+ "pdf"
				+ File.separator + fileName;				
		
		File file = new File(nowPath);
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String(fileName.getBytes("gbk"), "iso-8859-1")); // 转码之后下载的文件不会出现中文乱码
		response.addHeader("Content-Length", "" + file.length());
		try {
			// 以流的形式下载文件
			InputStream fis = new BufferedInputStream(new FileInputStream(
					nowPath));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 缩放图片
	 * @param source
	 * @param width 缩放 宽
	 * @param height 缩放 长
	 * @param isScaling 是否需要等比例缩放
	 * @return
	 */
	public static BufferedImage resize(BufferedImage source, int width,
			int height,boolean isScaling) {
		// width，height分别表示目标长和宽
		int type = source.getType();
		BufferedImage target = null;

		double sx = (double) width / source.getWidth();
		double sy = (double) height / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if(isScaling){
			if (sx > sy) {
				sx = sy;
				width = (int) (sx * source.getWidth());
			} else {
				sy = sx;
				height = (int) (sy * source.getHeight());			
			}
		}
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(width,
					height);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			target = new BufferedImage(width, height, type);
		Graphics2D g = target.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	public static int[] resize(int width, int height) {
		MathContext mc = new MathContext(3, RoundingMode.HALF_UP);
		BigDecimal resultWidth;
		BigDecimal resultHeight;
		BigDecimal bdwidth = new BigDecimal(width);
		BigDecimal bdheight = new BigDecimal(height);
		if (height > width) {
			resultHeight = new BigDecimal(320);
			resultWidth = resultHeight.multiply(bdwidth).divide(bdheight, mc);
		} else {
			resultWidth = new BigDecimal(320);
			resultHeight = resultWidth.multiply(bdheight).divide(bdwidth, mc);
		}
		return new int[] { resultWidth.intValue(), resultHeight.intValue() };
	}

	class uploadFile {
		Long filesize;
		String filename;
		String suffix;
		String filepath;
		String thumbPic;

		public uploadFile(String filename, String filepath) {
			super();
			this.filename = filename;
			this.filepath = filepath;
		}

		public uploadFile(Long filesize, String filename, String filepath,
				String suffix, String thumbPic) {
			super();
			this.filesize = filesize;
			this.filename = filename;
			this.filepath = filepath;
			this.suffix = suffix;
			this.thumbPic = thumbPic;
		}

		public uploadFile(Long filesize, String filename, String filepath,
				String suffix) {
			super();
			this.filesize = filesize;
			this.filename = filename;
			this.filepath = filepath;
			this.suffix = suffix;
		}

		public String getFilepath() {
			return filepath;
		}

		public void setFilepath(String filepath) {
			this.filepath = filepath;
		}

		public Long getFilesize() {
			return filesize;
		}

		public void setFilesize(Long filesize) {
			this.filesize = filesize;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public String getSuffix() {
			return suffix;
		}

		public void setSuffix(String suffix) {
			this.suffix = suffix;
		}

		public String getThumbPic() {
			return thumbPic;
		}

		public void setThumbPic(String thumbPic) {
			this.thumbPic = thumbPic;
		}

	}
}
