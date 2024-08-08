package com.example.myphone;

import jakarta.servlet.http.HttpServletRequest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.Arrays;


@SpringBootApplication
@MapperScan("com.example.myphone.mapper")
public class MyphoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyphoneApplication.class, args);

		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			System.out.println("IP Address: " + inetAddress.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	@RestController
	class SubmitController {


		/*	@PostMapping("/upload")
		public String upload(@RequestParam("uploadfile") MultipartFile uploadfile, HttpServletRequest request) {
			// 获取客户端 IP 地址
			String clientIp = request.getRemoteAddr();
			System.out.println("Client IP Address: " + clientIp);

			if (uploadfile.isEmpty()) {
				return "File is empty";
			}

			try {
				// 获取上传文件的原始文件名
				String fileName = uploadfile.getOriginalFilename();

				// 定义文件保存路径
				String filePath = "D:/test/" + fileName; // 确保此路径存在或调整为你希望保存的路径

				// 创建目标文件
				File destinationFile = new File(filePath);
				System.out.println(destinationFile+"destinationFile");
				// 保存文件
				uploadfile.transferTo(destinationFile);

				return "File uploaded successfully: " + fileName;
			} catch (IOException e) {
				e.printStackTrace();
				return "File upload failed: " + e.getMessage();
			}		// 获取客户端 IP 地址
			String clientIp = request.getRemoteAddr();
			System.out.println("Client IP Address: " + clientIp);
		}*/
		@PostMapping("/upload")
		public String upload(@RequestParam("uploadfile") MultipartFile uploadfile, HttpServletRequest request) {

			if (uploadfile.isEmpty()) {
				return "图片为空";
			}
			try {
				// 获取上传文件的原始文件名
				String fileName = uploadfile.getOriginalFilename();
				// 获取当前日期
				LocalDate currentDate = LocalDate.now();
				String year = String.valueOf(currentDate.getYear());
				String month = String.format("%02d", currentDate.getMonthValue());
				String day = String.format("%02d", currentDate.getDayOfMonth());

				// 定义文件保存目录，按年/月/日结构
				String dirPath = "/home/ubuntu/uploadedFiles/" + year + "/" + month + "/" + day + "/";
				File dir = new File(dirPath);
				if (!dir.exists()) {
					dir.mkdirs(); // 创建目录
				}

				// 定义文件保存路径
				String filePath = dirPath + fileName;

				// 读取文件内容为字节数组
				byte[] imageBytes = uploadfile.getBytes();

				// 保存为图片文件
				try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
					fos.write(imageBytes);
				}

				// 获取服务器地址
				String serverUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
				// 构建图片的URL路径
				String fileUrl = serverUrl + "/home/ubuntu/uploadedFiles/" + year + "/" + month + "/" + day + "/" + fileName;

				return "File uploaded successfully: " + fileUrl;
			} catch (IOException e) {
				e.printStackTrace();
				return "File upload failed: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace());
			}
		}

		@RequestMapping("/hello")
		public String submitData(HttpServletRequest request) {
			System.out.println("接受到了数据");
			// 获取客户端 IP 地址
			String clientIp = request.getRemoteAddr();
			System.out.println("Client IP Address: " + clientIp);
			return "Data received";
		}

}}





