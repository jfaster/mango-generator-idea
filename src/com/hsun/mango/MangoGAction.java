package com.hsun.mango;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author hsun
 * @version 1.0
 * @since 2017/4/26 14:36
 */
public class MangoGAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        VirtualFile file = DataKeys.VIRTUAL_FILE.getData(e.getDataContext());
        GlobalSearchScope searchScope = GlobalSearchScope.allScope(project);
        PsiClass[] names = PsiShortNamesCache.getInstance(project).getClassesByName(file.getNameWithoutExtension(), searchScope);
        PsiClass clz = null;
        for (PsiClass pc : names) {
            String path = pc.getContainingFile().getVirtualFile().getPath();
            if (path.equals(file.getPath())) {
                clz = pc;
                break;
            }
        }
        String qualifiedName = clz.getQualifiedName();
        PsiField[] fields = clz.getFields();
        String basePackage = qualifiedName.substring(0, qualifiedName.lastIndexOf("."));
        basePackage = basePackage.substring(0, basePackage.lastIndexOf("."));
        Application application = ApplicationManager.getApplication();
        MangoComponent component = application.getComponent(MangoComponent.class);
        try {
            component.g(file.getNameWithoutExtension(), basePackage, file.getParent().getParent().getPath(),fields);
        } catch (Exception e1) {
            Messages.showMessageDialog(project, String.format("代码生成失败\n%s", e1.getMessage()), "MangoG", Messages.getErrorIcon());
        }

        Messages.showMessageDialog(project, "代码生成成功", "MangoG", Messages.getInformationIcon());
    }

    @Override
    public void update(AnActionEvent e) {
        String extension = getFileExtension(e.getDataContext());
        this.getTemplatePresentation().setEnabled(extension != null && "java".equals(extension));
    }

    private static String getFileExtension(DataContext dataContext) {
        VirtualFile file = DataKeys.VIRTUAL_FILE.getData(dataContext);
        return file == null ? null : file.getExtension();
    }
}
