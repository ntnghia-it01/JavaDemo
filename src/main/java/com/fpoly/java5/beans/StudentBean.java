package com.fpoly.java5.beans;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentBean {
  // Không rỗng
  @NotBlank(message = "Họ và tên không được rỗng")
  private String name;
  // Không rỗng
  // Định dạng email
  @NotBlank(message = "Email không được rỗng")
  @Email(message = "Email không đúng định dạng")
  private String email;
  // Không rỗng
  // Độ dài đúng 10 ký tự
  @NotBlank(message = "Số điện thoại không được rỗng")
  @Length(min = 10, max = 10, message = "Số điện thoại phải có 10 ký tự")
  private String phone;
  // Bắt buộc chọn
  @Min(value = 1, message = "Giới tính không được rỗng")
  private int gender;
  // Không rỗng
  // Nằm trong khoản từ 0 -> 10
  @Range(min = 0, max = 10, message = "Điểm phải nằm trong khoảng từ 0 -> 10")
  private double point;
  // Bắt buộc chọn
  @Min(value = 0, message = "Ngành học bắt buộc chọn")
  private int major;
}
