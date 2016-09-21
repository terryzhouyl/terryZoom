package com.terry.util;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.terry.BusinessException;
import com.terry.CommonVar;
import com.terry.entity.specialbean.UploadFile;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 图片上传类 返回的图片路径一概皆为相对路径，即去掉项目路径前缀的相对路径
 * @author Administrator
 *
 */
public class ImageUtil {

	/**
	 * 多图片上传
	 * @param cmfile
	 * @param fsize
	 * @param fileFolder  ../../.. 形式，首位不需要"/"
	 * @return
	 */
	public static List<UploadFile> uploadMutiPicture(CommonsMultipartFile[] cmfiles, Integer fsize,String fileFolder) {
		
		//StringBuilder filePath = new StringBuilder();				
		List<UploadFile> picList = new ArrayList<>();
		for(CommonsMultipartFile cmfile:cmfiles){			
			//filePath.append(uploadPicture(cmfile,fsize,fileFolder));
			//filePath.append(",");
			
			picList.add(uploadPicture(cmfile,fsize,fileFolder));
		}
		return picList;
	}
	
	/**
	 * 上传图片 适用于android,pc 不适用于ios  
	 * @param request
	 * @param cmfile 文件请求类
	 * @param fsize 文件大小
	 * @param fileFolder 文件路径  ../../.. 形式，首位不需要"/"
	 */
	public static UploadFile uploadPicture(CommonsMultipartFile cmfile, Integer fsize,String fileFolder){
		Integer filesize = fsize;
		if (fsize != null) {
			filesize = filesize * 1024 * 1024;
		} else {
			fsize = 10;
			filesize = 1024 * 1024 * 10;
		}
		if (cmfile != null && !cmfile.isEmpty()) {
			try {
				if (cmfile.getSize() < filesize) {
					String filename = cmfile.getOriginalFilename(); 
					String suffix = filename.substring(filename
							.lastIndexOf("."));
					String newPath = "";
					
					//查看文件路徑是否存在
					FileUtil.getLocalFolder(CommonVar.IMG_PREFIX+"/"+fileFolder);

					if ((".gif.png.jpg.jpeg.bmp".indexOf(suffix.toLowerCase()) >= 0)) {
						newPath = fileFolder
								+ "/"
								+ FileUtil.newFileName(cmfile
										.getOriginalFilename());
					} else {
						throw new BusinessException("图片格式不正确");
					}

					//暂时保存在同根的目录下
					File file = new File(CommonVar.IMG_PREFIX+"/"+newPath);
					if (!file.exists()) {
						file.createNewFile();
					}
					cmfile.getFileItem().write(file);
					return	new UploadFile(fileFolder,file.getName());
				} else {
					throw new BusinessException("文件大小不能超过" + fsize + "M");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}				
	
	/**
	 * 剪裁图片成正方形
	 * @param request
	 * @param srcFile
	 * @param fileFolder
	 * @return
	 */
	public static UploadFile cutPicToSquare(String srcFile) {
	 	FileInputStream is = null;  
        ImageInputStream iis = null;  
        
        try {  
        	File file = new File(CommonVar.IMG_PREFIX+"/"+srcFile);
            // 如果源图片不存在  
            if (!file.exists()) {  
                throw new Exception("原图片不存在");  
            }  	  
            // 读取图片文件  
            is = new FileInputStream(CommonVar.IMG_PREFIX+"/"+srcFile);  
  
            // 获取文件格式  
            String ext = srcFile.substring(srcFile.lastIndexOf(".") + 1);  
  
            // ImageReader声称能够解码指定格式  
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(ext);  
            ImageReader reader = it.next();  
  
            // 获取图片流  
            iis = ImageIO.createImageInputStream(is);  
  
            // 输入源中的图像将只按顺序读取  
            reader.setInput(iis, true);  
  
            // 描述如何对流进行解码  
            ImageReadParam param = reader.getDefaultReadParam();  
            int img_x =  reader.getWidth(0);	//获取图片宽
            int img_y = reader.getHeight(0);	//获取图片高
            int differValue = 0;		//宽高差值一半
            Rectangle rect = null;
            
            if(img_x <= img_y){
            	differValue = (img_y - img_x)/2;
            	// 图片裁剪区域  
            	rect = new Rectangle(0, img_y-differValue-img_x, img_x, img_x);  
            }
            else{
            	differValue = (img_x - img_y)/2;
            	// 图片裁剪区域  
            	rect = new Rectangle(img_x-differValue-img_y, 0, img_y, img_y); 
            }
            	
            // 提供一个 BufferedImage，将其用作解码像素数据的目标  
            param.setSourceRegion(rect);  
  
            // 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象  
            BufferedImage bi = reader.read(0, param);  
  
            //保存新图片
            String newPath = srcFile.substring(0,srcFile.lastIndexOf("/"));	            	            
           // String filePath = newPath+"/cut_"+FileUtil.newFileName(file.getName());
            
            String filePath = newPath+"/cut_"+file.getName();            
            ImageIO.write(bi, ext, new File(CommonVar.IMG_PREFIX+"/"+filePath)); 
            return new UploadFile(newPath,"cut_"+file.getName());	            
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (is != null) {  
                    is.close();  
                }  
                if (iis != null) {  
                    iis.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  	
        return null;
	}
	
	
	/**
	 *  剪裁图片
	 * @param request
	 * @param srcFile
	 * @param fileFolder
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static void cutPic(String srcFile,String desFile,int x,int y,int width,int height){
		 FileInputStream is = null;  
	        ImageInputStream iis = null;  
	        try {  
	        	File file = new File(srcFile);
	            // 如果源图片不存在  
	            if (!file.exists()) {  
	                throw new Exception("原图片不存在");  
	            }  	  
	            // 读取图片文件  
	            is = new FileInputStream(srcFile);  
	  
	            // 获取文件格式  
	            String ext = srcFile.substring(srcFile.lastIndexOf(".") + 1);  
	  
	            // ImageReader声称能够解码指定格式  
	            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(ext);  
	            ImageReader reader = it.next();  
	  
	            // 获取图片流  
	            iis = ImageIO.createImageInputStream(is);  
	  
	            // 输入源中的图像将只按顺序读取  
	            reader.setInput(iis, true);  
	  
	            // 描述如何对流进行解码  
	            ImageReadParam param = reader.getDefaultReadParam();  
	  
	            // 图片裁剪区域  
	            Rectangle rect = new Rectangle(x, y, width, height);  
	  
	            // 提供一个 BufferedImage，将其用作解码像素数据的目标  
	            param.setSourceRegion(rect);  
	  
	            // 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象  
	            BufferedImage bi = reader.read(0, param);  
	  
	            // 保存新图片  	                
	            ImageIO.write(bi, ext, new File(desFile)); 
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } finally {  
	            try {  
	                if (is != null) {  
	                    is.close();  
	                }  
	                if (iis != null) {  
	                    iis.close();  
	                }  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	        }  	
	}
	
	/**
	 * 
	 * app压缩图片 640 * 320 格式
	 * @param request
	 * @param originalPicUrl
	 * @param isVertical 是否允许竖屏  允许：会等比例压缩  不允许：将本来是竖屏的图片压缩成横屏，图片会严重失真
	 * @param originalPicUrl : 源图片路径,短文件路径 本项目中 /resource/enjoylife/后面的图片路径
	 * @return
	 */
	public static UploadFile appResize(String originalPicUrl,Boolean isVertical) {
		// 网站缩略图默认宽度1024, app640
		int w = 640;
		String suoPath = null;
		UploadFile uploadFile = null;
		try {
			File sourcefile = new File(CommonVar.IMG_PREFIX+"/"+originalPicUrl);
			BufferedImage bi = ImageIO.read(new FileInputStream(sourcefile));
			int width = bi.getWidth();
			
			if (width > w || !isVertical) {
				//suoPath = originalPicUrl.substring(0,originalPicUrl.lastIndexOf("/"))+"/phone_"+FileUtil.newFileName(sourcefile.getName());
				suoPath = originalPicUrl.substring(0,originalPicUrl.lastIndexOf("/"))+"/phone_"+sourcefile.getName();
//				suoPath = new Uploader(request).getFolder("upload/phone") + "/"
//						+ FileUtil.newFileName(sourcefile.getName());
				BufferedImage srcImage = ImageIO.read(sourcefile);																																								
					
				int h = w * bi.getHeight() / width;
				if(bi.getHeight() > bi.getWidth()+10) { //如果是竖屏
					if(isVertical){ //如果允许竖屏
						srcImage = resize(srcImage, w, h,true); //使用同比例缩放
					}
					else{ //不允许竖屏
						h = 360;
						srcImage = resize(srcImage, w, h,false); //不使用同比例缩放
					}
				}
				else { //横屏					
					srcImage = resize(srcImage, w, h,true); //使用同比例缩放
				}

				Integer flag = suoPath.toLowerCase().indexOf(".png",
						suoPath.length() - 4);
				if (flag > 0) {
					ImageIO.write(srcImage, "PNG", new File(CommonVar.IMG_PREFIX+"/"+suoPath));
				} else {
					ImageIO.write(srcImage, "JPEG", new File(CommonVar.IMG_PREFIX+"/"+suoPath));
				}
			} else {
				// 缩略图和原图一致
				suoPath = originalPicUrl;
			}
			uploadFile = new UploadFile(suoPath);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadFile;
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
	
	/**
	 * 剪裁缩放成正方形小图片  使用thumnail开源工具
	 * @return
	 */
	public static UploadFile cutSmallPic(String originalPicUrl) {
		//先将图片剪裁成正方形
		//String cutPic =	cutPicToSquare(originalPicUrl);
		UploadFile uploadFile =	cutPicToSquare(originalPicUrl);
		
		//再将正方形缩放		
		//String cutSmallPicPath = originalPicUrl.substring(0,originalPicUrl.lastIndexOf("/"))+"/smallPic_"+FileUtil.newFileName(cutPic);
		String cutSmallPicPath = uploadFile.getFilePath()+"/smallPic_"+uploadFile.getFileName();		
		try {
			Thumbnails.of(CommonVar.IMG_PREFIX+"/"+uploadFile.getRealPath())
					 .size(160, 160)
					 .toFile(CommonVar.IMG_PREFIX+"/"+cutSmallPicPath);
			//刪除掉剪裁的大圖
			File file = new File(CommonVar.IMG_PREFIX+"/"+uploadFile.getRealPath());
			file.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new UploadFile(cutSmallPicPath);
	}
	
	public static void main(String[] args) {
		//String path = appResize("store/12/684741451.jpg",true);
		
		//String path = cutSmallPic("store/12/147263455451632.jpg");
		//System.out.println(path);
	}
}
