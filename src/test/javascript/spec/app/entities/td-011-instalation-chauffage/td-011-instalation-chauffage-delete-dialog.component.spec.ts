import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SuivEnergiesTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { TD011InstalationChauffageDeleteDialogComponent } from 'app/entities/td-011-instalation-chauffage/td-011-instalation-chauffage-delete-dialog.component';
import { TD011InstalationChauffageService } from 'app/entities/td-011-instalation-chauffage/td-011-instalation-chauffage.service';

describe('Component Tests', () => {
  describe('TD011InstalationChauffage Management Delete Component', () => {
    let comp: TD011InstalationChauffageDeleteDialogComponent;
    let fixture: ComponentFixture<TD011InstalationChauffageDeleteDialogComponent>;
    let service: TD011InstalationChauffageService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD011InstalationChauffageDeleteDialogComponent],
      })
        .overrideTemplate(TD011InstalationChauffageDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD011InstalationChauffageDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD011InstalationChauffageService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
