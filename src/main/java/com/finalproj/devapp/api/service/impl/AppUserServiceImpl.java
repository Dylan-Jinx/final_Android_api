package com.finalproj.devapp.api.service.impl;

import com.finalproj.devapp.api.domain.model.AppUser;
import com.finalproj.devapp.api.mapper.AppUserMapper;
import com.finalproj.devapp.api.service.AppUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-11-07
 */
@Service
public class AppUserServiceImpl extends ServiceImpl<AppUserMapper, AppUser> implements AppUserService {

}
