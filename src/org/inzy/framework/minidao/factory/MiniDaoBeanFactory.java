 package org.inzy.framework.minidao.factory;
 
 import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.inzy.framework.minidao.annotation.MiniDao;
import org.inzy.framework.minidao.util.MiniDaoUtil;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
 
 
 
 
 
 
 
 
 
 public class MiniDaoBeanFactory
   implements BeanFactoryPostProcessor
 {
   private static final Logger logger = Logger.getLogger(MiniDaoBeanFactory.class);
   
   private List<String> packagesToScan;
   
   public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
     throws BeansException
   {
     logger.debug("................MiniDaoBeanFactory................ContextRefreshed................begin...................");
     try
     {
       for (String pack : this.packagesToScan) {
         if (StringUtils.isNotEmpty(pack)) {
           Set<Class<?>> classSet = PackagesToScanUtil.getClasses(pack);
           for (Class<?> miniDaoClass : classSet) {
             if (miniDaoClass.isAnnotationPresent(MiniDao.class))
             {
               ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
               proxyFactoryBean.setBeanFactory(beanFactory);
               proxyFactoryBean.setInterfaces(new Class[] { miniDaoClass });
               proxyFactoryBean.setInterceptorNames(new String[] { "miniDaoHandler" });
               String beanName = MiniDaoUtil.getFirstSmall(miniDaoClass.getSimpleName());
               if (!beanFactory.containsBean(beanName))
               {
 
                 logger.info("MiniDao Interface [/" + miniDaoClass.getName() + "/] onto Spring Bean '" + beanName + "'");
                 beanFactory.registerSingleton(beanName, proxyFactoryBean.getObject());
               }
             }
           }
         }
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
     logger.debug("................MiniDaoBeanFactory................ContextRefreshed................end...................");
   }
   
   public List<String> getPackagesToScan()
   {
     return this.packagesToScan;
   }
   
   public void setPackagesToScan(List<String> packagesToScan) {
     this.packagesToScan = packagesToScan;
   }
}